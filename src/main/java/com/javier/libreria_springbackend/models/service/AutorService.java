package com.javier.libreria_springbackend.models.service;

import com.javier.libreria_springbackend.models.dao.IAutorDao;
import com.javier.libreria_springbackend.models.dto.AutorDto;
import com.javier.libreria_springbackend.models.entity.Autor;
import com.javier.libreria_springbackend.models.mapper.AutorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AutorService implements IAutorCRUDServiceImpl {

    @Autowired
    private IAutorDao autorDao;
    
    @Override
    @Transactional(readOnly = true)
    public List<Autor> findAll() {
        return autorDao.findAll();
    }

    @Override
    @Transactional
    public Autor save(Autor autor) {
        autorDao.save(autor);
        return autor;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AutorDto> findById(Long id) {
        Autor autor = autorDao.findById(id).orElse(null);
        if (autor != null) {
            return Optional.of(AutorMapper.toDto(autor));
        } else {
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
//        Autor autor = findById(id);
//        autorDao.delete(autor);
    }
}
