package Pattern1;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

/**
 * @author Axel Mathieu
 * @description Simulation of an actor firing a message to another one.
 */
public class FireAndForget {

	public static void main(String[] args) {
		// Instantiate the system actor.
		final ActorSystem system = ActorSystem.create("system");
		
		// Instantiate three actors
	    final ActorRef firstActor = system.actorOf(AnActor.createActor(), "firstActor");
	    final ActorRef secondActor = system.actorOf(AnActor.createActor(), "secondActor");
	    final ActorRef thirdActor = system.actorOf(AnActor.createActor(), "thirdActor");
	    
	    // Second and third actors say hello to first actor
	    secondActor.tell("Hello world !", firstActor);
	    thirdActor.tell("Hello world !", firstActor);
	    
	    try {
			waitBecauseTerminate();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			system.terminate();
		}
	}

	public static void waitBecauseTerminate() throws InterruptedException {
		Thread.sleep(1000);
	}
}
