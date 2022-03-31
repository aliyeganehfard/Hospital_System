package controller.service;

import model.entity.Turn;
import model.repository.impl.TurnRepositoryImpl;

public class TurnServiceImpl extends Service<TurnRepositoryImpl, Turn,Integer> {
    public TurnServiceImpl() {
        super(new TurnRepositoryImpl());
    }
}
