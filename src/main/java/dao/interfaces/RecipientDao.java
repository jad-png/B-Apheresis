package dao.interfaces;

import entity.Recipient;

import java.util.List;

public interface RecipientDao {

    // Base Crud methods
    Recipient save(Recipient rec);
    Recipient update(Recipient rec);
    boolean delete(Recipient rec);
    Recipient findById(Long id);
    List<Recipient> findAll();

}
