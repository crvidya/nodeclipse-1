 //javaextend.js

// http://hg.openjdk.java.net/nashorn/jdk8/nashorn/raw-file/29b2b2ed954c/docs/JavaScriptingProgrammersGuide.html#jsextendconcrete

/**
 * Results: 
size invoked!
add(i, e) invoked!

 */

var ArrayList = Java.type("java.util.ArrayList")
var ArrayListExtender = Java.extend(ArrayList)
var printSizeInvokedArrayList = new ArrayListExtender() {
    size: function() { print("size invoked!"); }
}
var printAddInvokedArrayList = new ArrayListExtender() {
    add: function(x, y) {
        if(typeof(y) === "undefined") {
            print("add(e) invoked!");
        } else {
            print("add(i, e) invoked!");
        }
    }
};
printSizeInvokedArrayList.size();
printAddInvokedArrayList.add(33, 33);