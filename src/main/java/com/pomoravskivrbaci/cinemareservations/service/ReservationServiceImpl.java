package com.pomoravskivrbaci.cinemareservations.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.pomoravskivrbaci.cinemareservations.model.Reservation;
import com.pomoravskivrbaci.cinemareservations.model.User;
import com.pomoravskivrbaci.cinemareservations.repository.ReservationRepository;

@Service
public class ReservationServiceImpl implements ReservationService{

	@Autowired
	private ReservationRepository reservationRepository;
	
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW)
	public List<Reservation> findAll() {
		// TODO Auto-generated method stub
		return reservationRepository.findAll();
	}

	@Override
	@Transactional(readOnly=false,isolation=Isolation.SERIALIZABLE)
	public Reservation save(Reservation reservation) {
		// TODO Auto-generated method stub
		return reservationRepository.save(reservation);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Reservation> findByInstitutionIdAndHallIdAndPeriodIdAndProjectionId(Long instID, Long hallID,
			Long periodID, Long projectionID) {
		// TODO Auto-generated method stub
		return reservationRepository.findByInstitutionIdAndHallIdAndPeriodIdAndProjectionId(instID, hallID, periodID, projectionID);
	}

	@Override
	@Transactional(readOnly=true)
	public Reservation findById(Long id) {
		// TODO Auto-generated method stub
		return reservationRepository.findOne(id);
	}

	@Override
	@Transactional(readOnly=false,isolation=Isolation.REPEATABLE_READ)
	public void update(User u,Long id) {
		// TODO Auto-generated method stub
		reservationRepository.update(u,id);
	}

	@Override
	@Transactional(readOnly=false,isolation=Isolation.READ_COMMITTED)
	public void delete(Long id) {
		// TODO Auto-generated method stub
		 reservationRepository.delete(id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Reservation> findByOwnerId(Long id) {
		// TODO Auto-generated method stub
		return reservationRepository.findByOwnerId(id);
	}

}
