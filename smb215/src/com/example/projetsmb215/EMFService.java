package com.example.projetsmb215;

public class EMFService {
    private static final EntityManagerFactory emfInstance = Persistence.createEntityManagerFactory("transactions-optional");
 
    private EMFService() {
    }
 
    public static EntityManagerFactory get() {
        return emfInstance;
    }
}
    