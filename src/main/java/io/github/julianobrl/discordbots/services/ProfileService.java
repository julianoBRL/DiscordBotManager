package io.github.julianobrl.discordbots.services;

import io.github.julianobrl.discordbots.entities.Profile;
import io.github.julianobrl.discordbots.entities.enums.ProfileRoles;
import io.github.julianobrl.discordbots.repositories.ProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProfileService  implements IService<Profile> {

    private final ProfileRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<Profile> list() {
        return repository.findAll();
    }

    @Override
    public List<Profile> search() {
        return List.of();
    }

    @Override
    public Profile create(Profile object) {
        object.setPassword(passwordEncoder.encode(object.getPassword()));
        if(repository.count() == 0){
            object.setRole(ProfileRoles.ADMIN);
        }
        return repository.save(object);
    }

    @Override
    public Profile getById(Long id) {
        return repository.getReferenceById(id);
    }

    @Override
    public Profile update(Long id, Profile updated) {
        return null;
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
