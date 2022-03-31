package controller.service;

import model.entity.Prescription;

public class PrescriptionServiceImpl extends Service<PrescriptionServiceImpl, Prescription,Integer> {
    public PrescriptionServiceImpl() {
        super(new PrescriptionServiceImpl());
    }
}
