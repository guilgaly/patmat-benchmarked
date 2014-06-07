package patmat

import scala.collection.immutable.Set
import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import patmat.Huffman._

@RunWith(classOf[JUnitRunner])
class HuffmanSuite extends FunSuite {
  trait TestTrees {
    val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
    val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
  }
  trait TestLists {
    val l1 = List('a', 'b', 'a')
    val l2 = List('a', 'b', 'a', 'c', 'a', 'z', 'a', 'z')
    val l3 = List(('a', 4), ('z', 2), ('c', 3),('b', 1))
  }

  test("weight of a larger tree") {
    new TestTrees {
      assert(weight(t1) === 5)
    }
  }

  test("number of occurences of characters") {
    new TestLists {
      assert(times(l1).toSet === Set(('a', 2), ('b', 1)))
      assert(times(l2).toSet === Set(('a', 4), ('b', 1), ('c', 1), ('z', 2)))
    }
  }

  test("chars of a larger tree") {
    new TestTrees {
      assert(chars(t2) === List('a','b','d'))
    }
  }

  test("string2chars(\"hello, world\")") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }

  test("makeOrderedLeafList for some frequency table") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
  }

  test("singleton") {
    new TestTrees {
      assert(singleton(List(t1)) === true)
      assert(singleton(List(t2)) === true)
      assert(singleton(List(t1, t2)) === false)
      assert(singleton(List()) === false)
    }
  }

  test("combine of some leaf list") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(combine(leaflist) === List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
  }

  test("decode and encode a very short text should be identity") {
    new TestTrees {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
    }
  }

  test("decode and encode a longer text should be identity") {
    new TestTrees {
      assert(decode(frenchCode, encode(frenchCode)("helloworldfromhuffman".toList)) === "helloworldfromhuffman".toList)
    }
  }

  test("decode and quick encode a very short text should be identity") {
    new TestTrees {
      assert(decode(t1, quickEncode(t1)("ab".toList)) === "ab".toList)
    }
  }

  test("decode and quick encode a longer text should be identity") {
    new TestTrees {
      assert(decode(frenchCode, quickEncode(frenchCode)("helloworldfromhuffman".toList)) === "helloworldfromhuffman".toList)
    }
  }
}
