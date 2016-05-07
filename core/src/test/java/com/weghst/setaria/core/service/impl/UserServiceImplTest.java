/**
 * Copyright (C) 2016 The Weghst Inc. <kevinz@weghst.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.weghst.setaria.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.weghst.setaria.core.SpringTestSupport;
import com.weghst.setaria.core.domain.User;
import com.weghst.setaria.core.service.PasswordNotMatchedException;
import com.weghst.setaria.core.service.UserNotFoundException;
import com.weghst.setaria.core.service.UserService;

import static org.testng.Assert.*;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class UserServiceImplTest extends SpringTestSupport {

    @Autowired
    private UserService userService;

    @DataProvider
    private Object[][] dataUser() {
        User user = new User();
        user.setEmail("kevinz@weghst.com");
        user.setPassword("[PASSWORD]");

        return new Object[][]{{user}};
    }

    @Test(dataProvider = "dataUser")
    public void testSave(User user) throws Exception {
        userService.save(user);

        assertTrue(user.getId() > 0);
    }

    @Test(dataProvider = "dataUser")
    public void testUpdate(User user) throws Exception {
        userService.save(user);

        user.setPassword("[NEW-PASSWORD]");
        userService.update(user);
    }

    @Test(dataProvider = "dataUser")
    public void testDeleteById(User user) throws Exception {
        userService.save(user);

        userService.deleteById(user.getId());
    }

    @Test(dataProvider = "dataUser")
    public void testFindByEmail(User user) throws Exception {
        userService.save(user);

        user = userService.findByEmail(user.getEmail());
        assertNotNull(user);
    }

    @Test(dependsOnMethods = "testSave")
    public void testFindAll() throws Exception {
        List<User> users = userService.findAll();
        assertTrue(users.size() > 0);
    }

    @Test(dataProvider = "dataUser", expectedExceptions = UserNotFoundException.class)
    public void testAuthenticate(User user) {
        userService.save(user);

        userService.authenticate("kevinzzzzzzzzzzz999999****@weghst.com", "88888888");
    }

    @Test(dataProvider = "dataUser", expectedExceptions = PasswordNotMatchedException.class)
    public void testAuthenticate1(User user) {
        userService.save(user);

        userService.authenticate(user.getEmail(), user.getPassword() + " - error");
    }

    @Test(dataProvider = "dataUser")
    public void testAuthenticate2(User user) {
        String password = user.getPassword();

        userService.save(user);
        user = userService.authenticate(user.getEmail(), password);
        assertNotNull(user);
    }
}