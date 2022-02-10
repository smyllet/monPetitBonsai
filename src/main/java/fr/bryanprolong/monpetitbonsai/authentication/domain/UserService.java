package fr.bryanprolong.monpetitbonsai.authentication.domain;

import fr.bryanprolong.monpetitbonsai.authentication.domain.exception.InvalidePasswordException;
import fr.bryanprolong.monpetitbonsai.authentication.domain.model.PasswordChangeRequest;
import fr.bryanprolong.monpetitbonsai.authentication.domain.model.User;
import fr.bryanprolong.monpetitbonsai.authentication.domain.model.UserCreationRequest;
import fr.bryanprolong.monpetitbonsai.authentication.modelMapper.UserMapper;
import fr.bryanprolong.monpetitbonsai.commons.dao.AuthorityDao;
import fr.bryanprolong.monpetitbonsai.commons.entity.AuthorityEntity;
import fr.bryanprolong.monpetitbonsai.commons.entity.UserEntity;
import fr.bryanprolong.monpetitbonsai.commons.entity.AuthorityId;
import fr.bryanprolong.monpetitbonsai.commons.dao.UserDao;
import fr.bryanprolong.monpetitbonsai.commons.type.AuthorityType;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private final UserDao userDao;
    private final AuthorityDao authorityDao;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserDao userDao, PasswordEncoder passwordEncoder, AuthorityDao authorityDao) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.authorityDao = authorityDao;
    }

    @Transactional
    public void create(UserCreationRequest userCreationRequest) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(userCreationRequest.getName());
        userEntity.setUsername(userCreationRequest.getUsername());
        userEntity.setPassword(passwordEncoder.encode(userCreationRequest.getPassword()));
        UserEntity savedUser = userDao.save(userEntity);

        List<AuthorityEntity> authorities = new ArrayList<>();
        authorities.add(new AuthorityEntity(AuthorityId.getDefaultAuthority(savedUser.getId())));
        savedUser.setAuthorities(authorities);

        userDao.save(savedUser);
    }

    public void updatePasswordUserByUsername(String username, PasswordChangeRequest passwordChangeRequest) throws InvalidePasswordException {
        UserEntity userEntity = userDao.findByUsername(username);
        if(passwordEncoder.matches(passwordChangeRequest.getOld_password(), userEntity.getPassword())) {
            userEntity.setPassword(passwordEncoder.encode(passwordChangeRequest.getPassword()));
            userDao.save(userEntity);
        } else {
            throw new InvalidePasswordException();
        }
    }

    public User getUserByUsername(String username) {
        return UserMapper.mapEntityToModel(userDao.findByUsername(username));
    }

    public List<User> getAllUser() {
        return userDao.findAll().stream().map(UserMapper::mapEntityToModel).collect(Collectors.toList());
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserEntity user = userDao.findByUsername(s);
        List<String> authoritiesList = userDao.findAuthorityByUserId(user.getId());
        String authorities = String.join(",", authoritiesList);
        return new AppUser(user.getId(), user.getUsername(), user.getPassword(),
                AuthorityUtils.commaSeparatedStringToAuthorityList(authorities));
    }

    public User changeAuthorityUserByUsername(String username, AuthorityType authorityType) {
        UserEntity user = userDao.findByUsername(username);
        userDao.removeAuthorityByUsername(user.getId());

        AuthorityId authorityId = new AuthorityId();
        authorityId.setUuid(user.getId());
        authorityId.setAuthority(authorityType.name());

        AuthorityEntity authorityEntity = new AuthorityEntity();
        authorityEntity.setAuthorityId(authorityId);


        authorityDao.save(authorityEntity);

        return UserMapper.mapEntityToModel(userDao.findByUsername(username));
    }
}
