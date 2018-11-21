package Pattern1;

import akka.actor.Props;
import akka.actor.AbstractActor;

public class AnActor extends AbstractActor {

	// Empty Constructor
	public AnActor() {}

	// Static function that creates actor
	public static Props createActor() {
		return Props.create(AnActor.class, () -> {
			return new AnActor();
		});
	}
	
	// Empty createReceive function
	// We have to override this function because this
	// class extends AbstractActor
	@Override
	public Receive createReceive() {
		return receiveBuilder()
				.build();
	}
}
