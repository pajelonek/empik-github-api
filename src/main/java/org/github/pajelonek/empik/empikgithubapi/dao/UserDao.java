package org.github.pajelonek.empik.empikgithubapi.dao;

import jakarta.persistence.*;
import lombok.extern.slf4j.Slf4j;
import org.github.pajelonek.empik.empikgithubapi.model.DefaultException;
import org.github.pajelonek.empik.empikgithubapi.model.Error;
import org.github.pajelonek.empik.empikgithubapi.model.UserEntity;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class UserDao {

    @PersistenceContext
    private final EntityManager entityManager;

    public UserDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public UserEntity getUserWithPessimisticWriteLock(String name) throws DefaultException {
        try {
            UserEntity user = entityManager.find(UserEntity.class, name, LockModeType.PESSIMISTIC_WRITE);

            if (user == null) {
                user = new UserEntity();
                user.setName(name);
                user.setRequestCount(0);
                entityManager.persist(user);
                entityManager.flush();
                entityManager.refresh(user, LockModeType.PESSIMISTIC_WRITE);
            }

            return user;
        }
        catch (PessimisticLockException e) {
            log.error("PessimisticLockException during getUserWithPessimisticWriteLock");
            throw new DefaultException(Error.USERINFO_PESSIMIST_LOCK_EXCEPTION_ERROR);
        }
        catch (EntityNotFoundException e) {
            log.error("EntityNotFoundException during getUserWithPessimisticWriteLock");
            throw new DefaultException(Error.USERINFO_ENTITY_NOT_FOUND_ERROR);
        }
        catch (DataAccessException e ) {
            log.error("DataAccessException during getUserWithPessimisticWriteLock");
            throw new DefaultException(Error.USERINFO_DATA_ACCESS_EXCEPTION_ERROR);
        }
        catch (Exception e) {
            log.error("UnexpectedException during getUserWithPessimisticWriteLock");
            throw new DefaultException(Error.USERINFO_UNEXPECTED_EXCEPTION_ERROR);
        }
    }

    public void incrementRequestCount(UserEntity user) throws DefaultException {
        try {
            user.setRequestCount(user.getRequestCount() + 1);
            entityManager.merge(user);
        }
        catch (PessimisticLockException e) {
            log.error("PessimisticLockException during getUserWithPessimisticWriteLock");
            throw new DefaultException(Error.USERINFO_PESSIMIST_LOCK_EXCEPTION_ERROR);
        }
        catch (EntityNotFoundException e) {
            log.error("EntityNotFoundException during getUserWithPessimisticWriteLock");
            throw new DefaultException(Error.USERINFO_ENTITY_NOT_FOUND_ERROR);
        }
        catch (DataAccessException e ) {
            log.error("DataAccessException during getUserWithPessimisticWriteLock");
            throw new DefaultException(Error.USERINFO_DATA_ACCESS_EXCEPTION_ERROR);
        }
        catch (Exception e) {
            log.error("UnexpectedException during getUserWithPessimisticWriteLock");
            throw new DefaultException(Error.USERINFO_UNEXPECTED_EXCEPTION_ERROR);
        }
    }

}
