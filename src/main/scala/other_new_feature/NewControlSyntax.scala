package other_new_feature

object NewControlSyntax:
  var x = 1
  val xs = List(2,3)
  val ys = List(4,5)

  def f(a: Int): Int = a

  if x < 0 then
    "negative"
  else if x == 0 then
    "zero"
  else
    "positive"
  
  if x < 0 then -x else x
  
  while x >= 0 do x = f(x)
  
  for x <- xs if x > 0
    yield x * x
  
  for
  x <- xs
  y <- ys
  do
    println(x + y)
  
  try f(x)
    catch case ex: Exception => throw ex
