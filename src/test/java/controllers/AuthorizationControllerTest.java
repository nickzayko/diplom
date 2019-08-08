package controllers;

import entity.UserEntity;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import service.UserServiceImpl;
import validators.AuthorizationValidator;

import javax.servlet.http.HttpSession;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.*;

public class AuthorizationControllerTest {

    private UserServiceImpl userServiceImpl;
    private AuthorizationValidator authorizationValidator;
    private Model model;
    private AuthorizationController controller;

    @Before
    public void setUp() throws Exception {
        userServiceImpl = EasyMock.createMock(UserServiceImpl.class);
        authorizationValidator = EasyMock.createMock(AuthorizationValidator.class);
        model = EasyMock.createMock(Model.class);
        controller = new AuthorizationController();
    }

    @After
    public void tearDown() throws Exception {
        userServiceImpl = null;
        authorizationValidator = null;
        model = null;
        controller = null;
    }

    @Test
    public void authorization() {
        expect(model.addAttribute("userEntity", new UserEntity())).andReturn(model);
        replay(model);

        controller.authorization(model);

        verify(model);
    }

    @Test
    public void authorization1() {

//
//        UserEntity userEntityMock = EasyMock.createMock(UserEntity.class);
//        BindingResult resultMock = EasyMock.createMock(BindingResult.class);
//        HttpSession httpSessionMock = EasyMock.createMock(HttpSession.class);
//        BindingResult bindingResult = EasyMock.createMock(BindingResult.class);
////        expect(authorizationValidator.validate(userEntityMock, bindingResult)).andVoid();
////        expect(bindingResult.hasErrors()).andReturn(false);
//        expect(userServiceImpl.getUserByLoginAndPassword(new String(), new String())).andReturn(new UserEntity());
//        replay(userServiceImpl);
//
//        controller.authorization(model, httpSessionMock, userEntityMock, bindingResult);
//
//        verify(userServiceImpl);
    }
}