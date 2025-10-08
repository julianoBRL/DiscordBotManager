package io.github.julianobrl.discordbots.services;

import io.github.julianobrl.discordbots.entities.App;
import io.github.julianobrl.discordbots.exceptions.AppException;
import io.github.julianobrl.discordbots.repositories.AppRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppService implements IService<App> {

    private final AppRepository repository;

    @Override
    public List<App> list() {
        return repository.findAll();
    }

    @Override
    public List<App> search() {
        return List.of();
    }

    @Override
    public App create(App object) {
        return repository.save(object);
    }

    @Override
    public App getById(Long id) {
        return repository.findById(id)
                .orElseThrow(()-> new AppException("App not found!", HttpStatus.NOT_FOUND));
    }

    @Override
    public App update(Long id, App updated) {
        return null;
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

}
