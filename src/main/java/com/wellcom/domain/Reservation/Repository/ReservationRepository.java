package com.wellcom.domain.Reservation.Repository;

import com.wellcom.domain.Member.Member;
import com.wellcom.domain.Reservation.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Reservation findByReservationIdAndMember(String reservationId, Member member);
    Optional<Reservation> findByReservationId(String reservationId);
    @Query("SELECT r FROM Reservation r WHERE r.desk.deskNum = :deskNum AND r.startTime > :currentTime AND (r.status = 'WAITING' OR r.status = 'USING')")
    List<Reservation> findActiveReservationsByDeskAndTime(@Param("deskNum") int deskNum, @Param("currentTime") LocalDateTime currentTime);
}
