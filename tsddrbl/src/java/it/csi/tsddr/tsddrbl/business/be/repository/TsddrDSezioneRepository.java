package it.csi.tsddr.tsddrbl.business.be.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.csi.tsddr.tsddrbl.business.be.entity.TsddrDSezione;

/**
 * The Interface TsddrDSezioneRepository.
 */
@Repository
public interface TsddrDSezioneRepository extends JpaRepository<TsddrDSezione, Long> {

    @Query("SELECT tdsez "
            + "FROM TsddrDSezione tdsez "
            + "WHERE tdsez.idSezione = :idSezione AND "
            + RepositoryUtil.TDSEZ_SEZIONE_VALIDITY_CHECK)
    Optional<TsddrDSezione> findByIdSezione(@Param("idSezione") Long idSezione, @Param("currentDate") Date currentDate);
    
}
