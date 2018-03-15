# "Trie" This Challenge


## This Implementation

 - Written by Sean McNamara <smcnam@gmail.com> on on 3/14/18
 - For the [Captivation Software Trie This Challenge](https://github.com/captivationsoftware/Trie-This-Challenge)
 - License: 
 	- [2-Clause BSD](https://opensource.org/licenses/BSD-2-Clause) for my code
 	- Vaadin: Apache License 2.0
 	- jUnit: Eclipse Public License 1.0
 	- jMockit: 2-Clause BSD
 	- AssertJ: Apache License 2.0
- Requires Java 9 and Maven 3.5 or later - everything else should be pulled in from public Maven repositories.

## How To Run

 1. Clone the repository.
 2. Run `mvn package install` from the root directory.
 3. To run the unit tests: `mvn test`
 4. `cd trie-this-web` to move to the UI project.
 5. `mvn jetty:run` to run the web server.
 	- By default the web server starts on 0.0.0.0:8080, so you should be able to access it via: [http://localhost:8080](http://localhost:8080)
 6. To use the web UI, add some elements to the trie by typing them in the text field then clicking the button. Then type a prefix to get a list of the words that match that prefix.

## Code Guide

 - Base directory: parent pom.xml for the Maven multi-module project.
 - trie-this-lib directory: contains the Trie implementation and unit tests. This is technically all the assignment required.
 - trie-this-web directory: contains the Vaadin web UI implementing a basic autocomplete field.


## Original Assignment

Build a simple autocomplete application based on a [Trie](https://en.wikipedia.org/wiki/Trie) data structure.
 
### Requirements
Create an implementation of a Trie, implementing the algorithms required to support the following interface:
* `add(word)` => adds *word* to the trie, returning `true` if the word was successfully added and `false` if the word was already present.
* `contains(word)` => returns `true` if the trie contains *word* and `false` if it does not.
* `search(prefix)` => returns the list of all words in the trie that begin with *prefix*. 
 
### Guidelines
* The trie implementation only needs to support lowercase words
* Clever solutions are welcome and encouraged
* Implement in a language of your choice
* Include instructions as to how to run your solution
* A User Interface is not required for successful completion of this task (no penalty if you include one, however)

Fork this repository to implement your solution. When you are finished, submit a pull request containing your Trie implementation and any supporting code. 
