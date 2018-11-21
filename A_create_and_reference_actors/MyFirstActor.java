package Pattern0;

import akka.actor.Props;
import akka.actor.AbstractActor;

public class MyFirstActor extends AbstractActor {

	// Empty Constructor
	public MyFirstActor() {}

	// Static function that creates actor
	public static Props createActor() {
		return Props.create(MyFirstActor.class, () -> {
			return new MyFirstActor();
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
