package com.hibernate.service.impl;

import com.hibernate.dao.StageDao;
import com.hibernate.model.Stage;
import com.hibernate.service.StageService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StageServiceImpl implements StageService {
    private final StageDao stageDao;

    @Autowired
    public StageServiceImpl(StageDao stageDao) {
        this.stageDao = stageDao;
    }

    @Override
    public Stage add(Stage stage) {
        return stageDao.add(stage);
    }

    @Override
    public Stage get(Long id) {
        return stageDao.get(id).get();
    }

    @Override
    public List<Stage> getAll() {
        return stageDao.getAll();
    }
}
