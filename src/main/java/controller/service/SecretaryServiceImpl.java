package controller.service;

import model.entity.Secretary;

public class SecretaryServiceImpl extends Service<SecretaryServiceImpl, Secretary,Integer> {
    public SecretaryServiceImpl() {
        super(new SecretaryServiceImpl());
    }
}
