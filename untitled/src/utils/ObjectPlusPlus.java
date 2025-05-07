package utils;

import java.io.PrintStream;
import java.io.Serializable;
import java.util.*;

public abstract class ObjectPlusPlus extends ObjectPlus implements Serializable {
    /**
     * Stores information about all connections of this object.
     */
    private Map<String, Map<Object, ObjectPlusPlus>> links = new Hashtable<>();
    /**
     * Stores information about all parts connected with any objects.
     */
    private static Set<ObjectPlusPlus> allParts = new HashSet<>();

    /**
     * The constructor.
     */
    public ObjectPlusPlus() {
        super();
    }

    private void addLink(String roleName, String reverseRoleName, ObjectPlusPlus targetObject, Object qualifier, int counter) {
        Map<Object, ObjectPlusPlus> objectLinks;
        // Protection for the reverse connection
        if (counter < 1) {
            return;
        }

        // Find a collection of links for the role
        if (links.containsKey(roleName)) {
            // Get the links
            objectLinks = links.get(roleName);
        } else {
            // No links ==> create them
            objectLinks = new HashMap<>();
            links.put(roleName, objectLinks);
        }

        // Check if there is already the connection
        // If yes, then ignore the creation
        if (!objectLinks.containsKey(qualifier)) {
            // Add a link for the target object
            objectLinks.put(qualifier, targetObject);

            // Add the reverse connection
            targetObject.addLink(reverseRoleName, roleName, this, this, counter - 1);
        }
    }

    public void addLink(String roleName, String reverseRoleName, ObjectPlusPlus targetObject, Object
            qualifier) {
        addLink(roleName, reverseRoleName, targetObject, qualifier, 2);
    }

    public void addLink(String roleName, String reverseRoleName, ObjectPlusPlus targetObject) {
        addLink(roleName, reverseRoleName, targetObject, targetObject);
    }

    public void addLink_subset(String roleName, String reverseRoleName, String superRoleName, ObjectPlusPlus targetObject) throws Exception {
        if (isLink(superRoleName, targetObject)) {
            // There is a (super) link to the added object in the super role
            // Create the link
            addLink(roleName, reverseRoleName, targetObject);
        } else {
            // No super link ==> exception
            throw new Exception("No link to the '" + targetObject + "' object in the '" + superRoleName + "' super role!");
        }
    }

    public void addPart(String roleName, String reverseRoleName, ObjectPlusPlus partObject) throws
            Exception {
        // Check if the part exist somewhere
        if (allParts.contains(partObject)) {
            throw new Exception("The part is already connected to a whole!");
        }

        addLink(roleName, reverseRoleName, partObject);

        // Store adding the object as a part
        allParts.add(partObject);
    }

    public ObjectPlusPlus[] getLinks(String roleName) throws Exception {
        Map<Object, ObjectPlusPlus> objectLinks;
        if (!links.containsKey(roleName)) {
            // No links for the role
            throw new Exception("No links for the role: " + roleName);
        }

        objectLinks = links.get(roleName);
        return (ObjectPlusPlus[]) objectLinks.values().toArray(new ObjectPlusPlus[0]);
    }

    public void showLinks(String roleName, PrintStream stream) throws Exception {
        Map<Object, ObjectPlusPlus> objectLinks;
        if (!links.containsKey(roleName)) {
            // No links
            throw new Exception("No links for the role: " + roleName);
        }

        objectLinks = links.get(roleName);
        Collection col = objectLinks.values();
        stream.println(this.getClass().getSimpleName() + " links, role '" + roleName + "':");

        for (Object obj : col) {
            stream.println(" " + obj);
        }
    }

    public ObjectPlusPlus getLinkedObject(String roleName, Object qualifier) throws Exception {
        Map<Object, ObjectPlusPlus> objectLinks;
        if(!links.containsKey(roleName)) {
            // No links
            throw new Exception("No links for the role: " + roleName);
        }

        objectLinks = links.get(roleName);
        if(!objectLinks.containsKey(qualifier)) {
            // No link for the qualifer
            throw new Exception("No link for the qualifer: " + qualifier);
        }

        return objectLinks.get(qualifier);
    }

    public boolean isLink(String roleName, ObjectPlusPlus targetObject) {
        Map<Object, ObjectPlusPlus> objectLink;
        if(!links.containsKey(roleName)) {
            // No links for the role
            return false;
        }
        objectLink = links.get(roleName);
        return objectLink.containsValue(targetObject);
    }

    public boolean anyLink(String roleName) {
        Map<Object, ObjectPlusPlus> objectLinks = links.get(roleName);
        return objectLinks != null && !objectLinks.isEmpty();
    }

    // My methods
    public void removeLink(String roleName, String reverseRoleName, ObjectPlusPlus targetObject) throws Exception {
        if (!links.containsKey(roleName)) {
            throw new Exception("No links for the role: " + roleName);
        }

        Map<Object, ObjectPlusPlus> roleLinks = links.get(roleName);
        Object keyToRemove = null;

        for (Map.Entry<Object, ObjectPlusPlus> entry : roleLinks.entrySet()) {
            if (entry.getValue().equals(targetObject)) {
                keyToRemove = entry.getKey();
                break;
            }
        }

        if (keyToRemove != null) {
            roleLinks.remove(keyToRemove);

            // Usuwanie powiązania zwrotnego
            if (targetObject.links.containsKey(reverseRoleName)) {
                Map<Object, ObjectPlusPlus> reverseLinks = targetObject.links.get(reverseRoleName);
                reverseLinks.values().remove(this);
            }
        }
    }
}