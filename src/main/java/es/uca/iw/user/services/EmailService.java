package es.uca.iw.user.services;


import es.uca.iw.user.domain.User;

public interface EmailService {

    boolean sendRegistrationEmail(User user);

}