/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.group06.logic;

import java.util.UUID;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import ua.group06.business.SessionFacadeLocal;
import ua.group06.persistence.Session;

/**
 *
 * @author matson
 */
@Stateless
public class SessionService implements SessionServiceLocal {
    @EJB
    private SessionFacadeLocal sessionFacade;

    @Override
    public Session create(long uid) {
        String token = generateToken();
        Session session = new Session(uid, token);
        sessionFacade.create(session);
        return session;
    }

    @Override
    public Session findByToken(String token) {
        return sessionFacade.findByToken(token);
    }

    // Generate unique token for user session. This will be used for authentication.
    private String generateToken() {
        String token = UUID.randomUUID().toString();
        while (sessionFacade.tokenExists(token)) {
            token = UUID.randomUUID().toString();
        }
        return token;
    }

}
