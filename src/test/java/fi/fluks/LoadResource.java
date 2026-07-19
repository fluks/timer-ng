package fi.fluks;

import java.io.InputStream;

public class LoadResource {
    private static final Class _class = LoadResource.class;
    
    /** Load a resource.
     * @param resource A path to resource.
     * @return A stream to resource.
     * @throws RuntimeException If Can't get stream to the resource.
     */
    public static InputStream loadResource(String resource) {
        InputStream is = _class.getResourceAsStream(resource);
        if (is == null)
            throw new RuntimeException("Can't get resource, " + resource);

        return is;
    }
}
