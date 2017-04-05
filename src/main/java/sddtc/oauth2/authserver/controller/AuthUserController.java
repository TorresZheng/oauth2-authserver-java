package sddtc.oauth2.authserver.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by hchang on 2017/4/5.
 */
@RestController
public class AuthUserController {

    @RequestMapping(value = "/oauthuser")
    public Principal user(Principal user) {
        return user;
    }
}
