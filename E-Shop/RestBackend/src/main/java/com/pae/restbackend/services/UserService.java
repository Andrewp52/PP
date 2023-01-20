package com.pae.restbackend.services;

import com.pae.restbackend.dto.AbstractDto;
import com.pae.restbackend.dto.AddressDto;
import com.pae.restbackend.dto.PhoneDto;
import com.pae.restbackend.dto.UserDto;
import com.pae.restbackend.entities.entityfactories.EntityFactory;
import com.pae.restbackend.entities.userdata.Address;
import com.pae.restbackend.entities.userdata.Phone;
import com.pae.restbackend.entities.userdata.Role;
import com.pae.restbackend.entities.userdata.User;
import com.pae.restbackend.exceptions.EntityNotFoundException;
import com.pae.restbackend.exceptions.NewEntityIdIsNotNullException;
import com.pae.restbackend.exceptions.PhoneNumberEmptyException;
import com.pae.restbackend.exceptions.UpdatableEntityIdIsNullException;
import com.pae.restbackend.repositories.BaseRepository;
import com.pae.restbackend.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UserService extends AbstractService<User, Long> implements UserDetailsService {
    private final PasswordEncoder passwordEncoder;
    private final AbstractService<Role, Long> roleService;
    private final AbstractService<Phone, Long> phoneService;
    private final AbstractService<Address, Long> addressService;
    @Autowired
    public UserService(BaseRepository<User, Long> repository,
                       PasswordEncoder passwordEncoder,
                       AbstractService<Role, Long> roleService,
                       AbstractService<Phone, Long> phoneService,
                       EntityFactory<User> factory, AbstractService<Address, Long> addressService) {
        super(repository, factory);
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
        this.phoneService = phoneService;
        this.addressService = addressService;
    }

    @Override
    public User addNew(User user) {
        if(user.getId() != null){
            throw new NewEntityIdIsNotNullException("New user id must be null!");
        }
        return repository.save(prepareNewUser(user));
    }

    @Override
    public User addNew(AbstractDto dto){
        if(dto.getId() != null){
            throw new NewEntityIdIsNotNullException("New user id must be null!");
        }
        return repository.save(prepareNewUser(dto));
    }
    @Override
    public User update(AbstractDto dto) {
        if(dto.getId() == null){
            throw new UpdatableEntityIdIsNullException("Updatable user is mandatory!");
        }
        User u = this.findById(dto.getId());
        UserDto d = (UserDto) dto;
        u.setUserName(d.getUserName());
        u.setFirstName(d.getFirstName());
        u.setLastName(d.getLastName());
        return repository.save(u);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return ((UsersRepository)repository).findByUserName(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found!")
        );
    }

    public Phone updatePhone(Long userId, PhoneDto dto) {
        if(dto.getPhone() == null || dto.getPhone().isEmpty()){
            throw new PhoneNumberEmptyException();
        }
        User u = this.findById(userId);
        Phone toUpdate = u.getPhones().stream()
                .filter(p -> p.getId().equals(dto.getId()))
                .findFirst()
                .orElseThrow(EntityNotFoundException::new);
        if(!toUpdate.getPhone().equals(dto.getPhone())){
            Phone p = ((PhoneService)phoneService).findOrCreate(dto);
            u.getPhones().remove(toUpdate);
            u.getPhones().add(p);
            repository.save(u);
            return p;
        }
        return toUpdate;
    }

    public void deletePhone(Long userId, Long phoneId) {
        User user = this.findById(userId);
        assert user.getPhones() != null;
        user.getPhones().removeIf(phone -> phone.getId().equals(phoneId));
        repository.save(user);
    }

    public Phone addPhone(Long userId, PhoneDto dto) {
        if(dto.getPhone() == null || dto.getPhone().isEmpty()){
            throw new PhoneNumberEmptyException();
        }
        if(dto.getId() != null){
            throw new NewEntityIdIsNotNullException();
        }
        User user = this.findById(userId);
        Phone phone = ((PhoneService)phoneService).findOrCreate(dto);
        if(user.getPhones() == null){
            user.setPhones(new HashSet<>());
        }
        user.getPhones().add(phone);
        repository.save(user);
        return phone;
    }

    public Address addAddress(Long userId, AddressDto dto) {
        if(dto.getId() != null){
            throw new NewEntityIdIsNotNullException();
        }
        User user = this.findById(userId);
        Address addr = ((AddressService)addressService).findOrCreate(dto);
        if(user.getPhones() == null){
            user.setAddresses(new HashSet<>());
        }
        user.getAddresses().add(addr);
        repository.save(user);
        return addr;
    }

    public void deleteAddress(Long userId, Long addressId) {
        User user = this.findById(userId);
        assert user.getAddresses() != null;
        user.getAddresses().removeIf(address -> address.getId().equals(addressId));
        repository.save(user);
    }

    public Address updateAddress(Long userId, AddressDto dto) {
        if(dto.getId() == null){
            throw new UpdatableEntityIdIsNullException();
        }
        User u = this.findById(userId);
        Address toUpdate = u.getAddresses().stream()
                .filter( a-> a.getId().equals(dto.getId()))
                .findFirst()
                .orElseThrow(EntityNotFoundException::new);
        if(isAddressChanged(toUpdate, dto)){
            Address a = ((AddressService)addressService).findOrCreate(dto);
            u.getAddresses().remove(toUpdate);
            u.getAddresses().add(a);
            repository.save(u);
            return a;
        }
        return toUpdate;
    }

    private User prepareNewUser(User u){
        u.setPassword(passwordEncoder.encode(u.getPassword()));
        u.setEnabled(true);
        Role r = ((RoleService)roleService).findByRole("USER");
        u.setRoles(new HashSet<>());
        u.getRoles().add(r);
        return u;
    }

    private User prepareNewUser(AbstractDto dto){
        User u = entityFactory.getEntity(dto);
        return prepareNewUser(u);
    }

    private boolean isAddressChanged(Address a, AddressDto dto){
        return !(
//                a.getRegion().equals(dto.getRegion())
//                || a.getArea().equals(dto.getArea())
                 a.getCity().equals(dto.getCity())
                && a.getStreet().equals(dto.getStreet())
                && a.getBuilding().equals(dto.getBuilding())
                && a.getAptOffice().equals(dto.getAptOffice())
        );
    }

}
