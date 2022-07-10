fun main(){
    //1-st stage
    print("Enter name of this bot: ")
    val botName : String = readln()
    print("OK, and now enter year of it`s creating (4 numbers): ")
    var yearOfCreating : Int? = readln().toIntOrNull()
    if (yearOfCreating == null){
        yearOfCreating = 0
    }
    while (yearOfCreating != null && (yearOfCreating < 1000 || yearOfCreating > 9999)){
        println("\n\t\tOooops, you entered wrong year. Please, try again...\n")
        print("OK, and now enter year of it`s creating (4 numbers): ")
        yearOfCreating = readln().toIntOrNull()
        if (yearOfCreating == null){
            yearOfCreating = 0
        }
    }
    println("Hello! My name is $botName. \nI was created in $yearOfCreating.")

    //2-nd stage
    print("Please, remind me your name: ")
    val nameOfUser : String = readln()
    println("What a great name you have, $nameOfUser!")

    //3-rd stage
    print("Let me guess your age.\nEnter remainders of dividing your age: \n\tby 3: ")
    var remainder3 : Int? = readln().toIntOrNull()
    while (remainder3 == null || remainder3 >= 3 || remainder3 < 0){
        print("\t\t  Remainder must be numeric, less than 3 and more than -1 :) \n\tPlease, try again: ")
        remainder3 = readln().toIntOrNull()
    }
    print("\tby 5: ")
    var remainder5 : Int? = readln().toIntOrNull()
    while (remainder5 == null || remainder5 >= 5 || remainder5 < 0){
        print("\t\t  Remainder must be numeric, less than 5 and more than -1 :) \n\tPlease, try again: ")
        remainder5 = readln().toIntOrNull()
    }
    print("\tby 7: ")
    var remainder7 : Int? = readln().toIntOrNull()
    while (remainder7 == null || remainder7 >= 7 || remainder7 < 0){
        print("\t\t  Remainder must be numeric, less than 7 and more than -1 :) \n\tPlease, try again: ")
        remainder7 = readln().toIntOrNull()
    }
    println("Your age is ${(remainder3 * 70 + remainder5 * 21 + remainder7 * 15) % 105}; that`s a good time to start programming!")

    //4-th stage
    println("I will prove to you that can count to any number you want:")
    var iter : Int? = readln().toIntOrNull()
    while (iter == null || iter < 0){
        println("\t\tSorry, only positive numbers ^_^ . Try again, please: ")
        iter = readln().toIntOrNull()
    }
    val tempIter = iter + 1
    while (iter > 0){
        println("${tempIter - iter}!")
        iter--
    }

    //5-th stage
    println("And now the last feature which I have: QUIZ!! Answer the question by entering number of option you think is correct.")
    println("Question: What is the best programming language for Android app development?\n" +
            "\t1. Java\n" +
            "\t2. HTML :)\n" +
            "\t3. Kotlin\n" +
            "\t4. Python")

    while (true){
        print("\nYour answer is ")
        var answer : Int? = readln().toIntOrNull()
        when (answer) {
            1 -> println("Oops, wrong answer. Please, try again")
            2 -> println("Are you seriously?  Please, try again")
            3 -> {
                println("Great, you are right!")
                break
            }
            4 -> println("Oops, wrong answer. Please, try again")
            else -> {
                println("Please, enter ONLY NUMBER of answer. Try again")
            }
        }
    }
    println("Good bye, have a nice day!")
}
