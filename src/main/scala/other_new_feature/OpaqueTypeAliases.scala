package other_new_feature

object OpaqueTypeAliases:
  object Logarithms:
    opaque type Logarithm = Double
    object Logarithm:
      def apply(d: Double): Logarithm = math.log(d)
      def safe(d: Double): Option[Logarithm] =
        if d > 0.0 then Some(math.log(d)) else None

    extension (x: Logarithm)
      def toDouble: Double = math.exp(x)
      def + (y: Logarithm): Logarithm = Logarithm(math.exp(x) + math.exp(y))
      def * (y: Logarithm): Logarithm = x + y

  object Access:
    opaque type Permissions = Int
    opaque type PermissionChoice = Int
    opaque type Permission <: Permissions & PermissionChoice = Int
  
    extension (x: Permissions)
      def & (y: Permissions): Permissions = x & y
    extension (x: PermissionChoice)
      def | (y: PermissionChoice): PermissionChoice = x | y
    extension (granted: Permissions)
      def is(required: Permissions) = (granted & required) == required
    extension (granted: Permissions)
      def isOneOf(required: PermissionChoice) = (granted & required) != 0
  
    val NoPermission: Permission = 0
    val Read: Permission = 1
    val Write: Permission = 2
    val ReadWrite: Permissions = Read & Write
    val ReadOrWrite: PermissionChoice = Read | Write

  object User:
    import Access.*
  
    case class Item(rights: Permissions)
  
    val roItem = Item(Read)  // OK, since Permission <: Permissions
    val rwItem = Item(ReadWrite)
    val noItem = Item(NoPermission)
  
    assert(!roItem.rights.is(ReadWrite))
    assert(roItem.rights.isOneOf(ReadOrWrite))
    assert(rwItem.rights.is(ReadWrite))
    assert(rwItem.rights.isOneOf(ReadOrWrite))
  
    assert(!noItem.rights.is(ReadWrite))
    assert(!noItem.rights.isOneOf(ReadOrWrite))