package be.technifutur.dao;

import java.util.List;
import java.util.Optional;

public interface CrudDAO<TENTITY, TID> {


    //Create
    void insert(TENTITY entity);


    //Read
    List<TENTITY> getAll();
    Optional<TENTITY> getOne( TID id );


    //Update
    void update(TID id, TENTITY entity);


    //Delete
    void delete(TID id);



}
