package new_type.polymorphic_function_types

// polymorphic method:
def foo[A](xs: List[A]): List[A] = xs.reverse

// polymorphic function value 値としてfooみたいな関数を受け渡せるようになった
val bar: [A] => List[A] => List[A] = [A] => (xs: List[A]) => foo[A](xs)

