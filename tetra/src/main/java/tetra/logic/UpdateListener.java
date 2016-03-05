package tetra.logic;

/**
 * A general interface for describing a subscriber that receives updates. These
 * updates do not contain parameters. The only information passed is that there
 * has been an update.
 * <p>
 * This interface is used to achieve further decoupling between game logic, and
 * the user interface. This interface can be used as a substitute for more
 * conrete interfaces, if introducing them would create unnecessary
 * dependencies.
 */
public interface UpdateListener {

    /**
     * Called when an update occurs.
     */
    void update();

}
