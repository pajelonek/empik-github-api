package org.github.pajelonek.empik.empikgithubapi.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.LockModeType;
import jakarta.persistence.PessimisticLockException;
import org.github.pajelonek.empik.empikgithubapi.model.DefaultException;
import org.github.pajelonek.empik.empikgithubapi.model.UserEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserDaoTest {

    @Mock
    private EntityManager entityManager;

    private UserDao userDao;

    public UserDaoTest() {
    }

    @BeforeEach
    void setUp() {
        this.userDao = new UserDao(entityManager);
    }

    @AfterEach
    void tearDown() {
        verifyNoMoreInteractions(entityManager);
    }

    @Test
    void getUserWithPessimisticWriteLock_userExists() throws DefaultException {
        // given
        String name = "testUser";
        UserEntity existingUser = new UserEntity();
        existingUser.setName(name);
        given(entityManager.find(UserEntity.class, name, LockModeType.PESSIMISTIC_WRITE)).willReturn(existingUser);

        // when
        UserEntity result = userDao.getUserWithPessimisticWriteLock(name);

        // then
        assertEquals(existingUser, result);
        verify(entityManager).find(UserEntity.class, name, LockModeType.PESSIMISTIC_WRITE);
    }

    @Test
    void getUserWithPessimisticWriteLock_userDoesNotExist() throws DefaultException {
        // given
        String name = "testUser";
        given(entityManager.find(UserEntity.class, name, LockModeType.PESSIMISTIC_WRITE)).willReturn(null);

        // when
        UserEntity result = userDao.getUserWithPessimisticWriteLock(name);

        // then
        assertNotNull(result);
        assertEquals(name, result.getName());
        assertEquals(0, result.getRequestCount());
        verify(entityManager).persist(any(UserEntity.class));
        verify(entityManager).flush();
        verify(entityManager).refresh(any(UserEntity.class), eq(LockModeType.PESSIMISTIC_WRITE));
    }

    @Test
    void getUserWithPessimisticWriteLock_pessimisticLockException() {
        String name = "testUser";

        given(entityManager.find(UserEntity.class, name, LockModeType.PESSIMISTIC_WRITE)).willThrow(PessimisticLockException.class);

        assertThrows(DefaultException.class, () -> userDao.getUserWithPessimisticWriteLock(name));
    }

    @Test
    void getUserWithPessimisticWriteLock_entityNotFoundException() {
        String name = "testUser";

        given(entityManager.find(UserEntity.class, name, LockModeType.PESSIMISTIC_WRITE)).willThrow(EntityNotFoundException.class);

        assertThrows(DefaultException.class, () -> userDao.getUserWithPessimisticWriteLock(name));
    }

    @Test
    void incrementRequestCount() throws DefaultException {
        // given
        UserEntity user = new UserEntity();
        user.setName("testUser");
        user.setRequestCount(5);

        // when
        userDao.incrementRequestCount(user);

        // then
        assertEquals(6, user.getRequestCount());
        verify(entityManager).merge(user);
    }
}