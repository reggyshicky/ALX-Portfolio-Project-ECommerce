package com.reginah.Expensetrackerapi.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    //serialVersionUID is a special static field that is used during to identifyy the version
    //of the class that was serialized. It is used during the deserialization process to ensure that the sender
    //and receiver of a serialized obj have loaded classes for that obj that are compatible with each other in terms of serialization
    //Versioning: The serialVersionUID is used to ensure that the serialized object can be deserialized correctly by the receiving system.
    // If the serialVersionUID of the class at the time of serialization does not match the serialVersionUID of the class at the time of
    // deserialization, an InvalidClassException will be thrown.

    public ResourceNotFoundException(String message) {
        super(message);
    }

}
