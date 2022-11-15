package be.vinci.ipl.chattycar.trips;

import be.vinci.ipl.chattycar.trips.models.NewTrip;
import be.vinci.ipl.chattycar.trips.models.Trip;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class TripsService {

  private final TripsRepository repository;

  public TripsService(TripsRepository repository) {
    this.repository = repository;
  }

  /**
   * Creates a trip
   * @param newTrip NewTrip to create
   * @return The trip if the trip could be created, null if another trip exists with this pseudo
   */
  public Trip createOne(NewTrip newTrip) {
    if (repository.existsByOriginAndDestinationAndDepartureAndDriver_id(newTrip.getOrigin(), newTrip.getDestination(), newTrip.getDeparture(),
        newTrip.getDriver_id())) return null;
    return repository.save(newTrip.toTrip());
  }

  /**
   * Reads a trip
   * @param id int of the trip
   * @return The trip found, or null if the trip couldn't be found
   */
  public Trip readOne(int id) {
    return repository.findById(id).orElse(null);
  }

  /**
   * Reads all trips
   * @return All trips found.
   */
  public Iterable<Trip> readAll() {
    return repository.findAll();
  }


  /**
   * Deletes a trip
   * @param id int of the trip
   * @return True if the trip could be deleted, false if the trip couldn't be found
   */
  public Trip deleteOne(int id) {
    Trip trip = repository.findById(id).orElse(null);
    if (trip == null) return null;
    repository.deleteById(id);
    return trip;
  }

  /**
   * Reads the driver list trips.
   * @param id int of the driver
   * @return All the trip found.
   */
  public Iterable<Trip> readOneByDriver(int id) {
    return repository.findByDriver_id(id);
  }

  /**
   * Delete all the driver list trips.
   * @param id int of the driver
   * @return All the trip found.
   */
  public Iterable<Trip> deleteAllByDriver(int id) {
    return repository.deleteByDriver_id(id);
  }


}