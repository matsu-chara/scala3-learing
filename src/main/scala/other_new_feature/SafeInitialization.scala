package other_new_feature

object SafeInitialization:
  abstract class AbstractFile:
    def name: String
    val extension: String = name.substring(4).nn

//  7 |	val localFile: String = s"${url.##}.tmp"  // error: usage of `localFile` before it's initialized
//    |	    ^
//    |    Access non-initialized field value localFile. Calling trace:
//    |     -> val extension: String = name.substring(4)	[ AbstractFile.scala:3 ]
//    |      -> def name: String = localFile            	[ AbstractFile.scala:8 ]
//  class RemoteFile(url: String) extends AbstractFile:
//     val localFile: String = s"${url.##}.tmp"  // error: usage of `localFile` before it's initialized
//     def name: String = localFile
     