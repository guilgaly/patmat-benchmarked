package patmat.benchmark

import patmat.Huffman._
import annotation.tailrec
import com.google.caliper.Param

// a caliper benchmark is a class that extends com.google.caliper.Benchmark
// the SimpleScalaBenchmark trait does it and also adds some convenience functionality
class Benchmark extends SimpleScalaBenchmark {
  
  // to make your benchmark depend on one or more parameterized values, create fields with the name you want
  // the parameter to be known by, and add this annotation (see @Param javadocs for more details)
  // caliper will inject the respective value at runtime and make sure to run all combinations 
  // @Param(Array("10", "100", "1000", "10000"))
  @Param(Array("1", "100"))
  val lengths: Int = 0
  @Param(Array("toto", "bonjouratousceciestuntestdeperformancesavecunephrasetreslongue"))
  val sentences: String = ""

  var length: Int = _
  var sentence: List[Char] = _
  
  override def setUp() {
    // set up all your benchmark data here
    length = lengths
    sentence = sentences.toList
  }
  
  // the actual code you'd like to test needs to live in one or more methods
  // whose names begin with 'time' and which accept a single 'reps: Int' parameter
  // the body of the method simply executes the code we wish to measure, 'reps' times
  // you can use the 'repeat' method from the SimpleScalaBenchmark trait to repeat with relatively low overhead
  // however, if your code snippet is very fast you might want to implement the reps loop directly with 'while'
  def timeEncode(reps: Int) = repeat(reps) {
    //////////////////// CODE SNIPPET THREE ////////////////////
    
    var result: List[Bit] = List()
    var i = 0
    def frenchEncode = encode(frenchCode)_
    while (i < length) {
      result = frenchEncode(sentence)
      i = i + 1 
    }
    result
    
    //////////////////////////////////////////////////////////
  }

  def timeQuickEncode(reps: Int) = repeat(reps) {
    //////////////////// CODE SNIPPET THREE ////////////////////

    var result: List[Bit] = List()
    var i = 0
    def frenchEncode = quickEncode(frenchCode)_
    while (i < length) {
      result = frenchEncode(sentence)
      i = i + 1
    }
    result

    //////////////////////////////////////////////////////////
  }

  // this is a scala version of Javas "for" loop, we test it against the array.foreach and a plain "while" loop
  @tailrec
  final def tfor[@specialized T](i: T)(test: T => Boolean, inc: T => T)(f: T => Unit) {
    if(test(i)) {
      f(i)
      tfor(inc(i))(test, inc)(f)
    }
  }
  
  override def tearDown() {
    // clean up after yourself if required
  }
  
}

