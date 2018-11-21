package Pattern0;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

/**
 * @author Axel Mathieu
 * @description Create an actor by passing the reference of another actor.
 */
public class CreateAndReferenceByConstruction {

	public static void main(String[] args) {
		final ActorSystem system = ActorSystem.create("system");
	    final ActorRef firstActor = system.actorOf(MyFirstActor.createActor(), "firstActor");
	    @SuppressWarnings("unused")
		final ActorRef secondActor = system.actorOf(MySecondActor.createActor(firstActor), "secondActor");

	    // Be careful, main thread will never terminate.
	    // We wait 1 second before ending system (by default)
	    // But this is not the best solution.
	    try {
			waitBeforeTerminate();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			system.terminate();
		}
	}

	public static void waitBeforeTerminate() throws InterruptedException {
		Thread.sleep(1000);
	}
}
