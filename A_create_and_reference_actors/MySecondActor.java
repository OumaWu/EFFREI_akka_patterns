package Pattern0;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class MySecondActor extends AbstractActor{

	// Logger attached to actor
	private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);
	// Actor reference to persist (neighbor actor)
	private ActorRef actorRef;

	public MySecondActor() {}

	// Constructor setting actor reference
	public MySecondActor(ActorRef actorRef) {
		this.actorRef = actorRef;
		log.info("I was linked to actor reference {}", this.actorRef);
	}

	// Static function creating actor
	public static Props createActor(ActorRef actorRef) {
		return Props.create(MySecondActor.class, () -> {
			return new MySecondActor(actorRef);
		});
	}

	// Static function creating actor
	public static Props createActor() {
		return Props.create(MySecondActor.class, () -> {
			return new MySecondActor();
		});
	}
	
	// We have to override this function because this
	// class extends AbstractActor
	@Override
	public Receive createReceive() {
		return receiveBuilder()
				// When receiving a new message containing a string
				// Actor updates his neighbor reference.
				.match(String.class, s -> {
					this.actorRef = getSender();
					log.info("Actor reference updated ! New reference is: {}", this.actorRef);
				})
				.build();
	}
}
