# Inverter-Index
Class Project

Main#1) Obtain your corpus of documents for the semester. To do so, come up with 10 neutral (ie no controversy) queries (for example: Who was the 16th President?) that you will submit to your search engine. You are to then download the first 20 (non-controversial) webpage responses that the search engine returns with, for each of the 10 queries. There will be a total of 200 html files. (We will be discussing shortly how to process these using the Java Regex package. You may NOT use 3rd party code. You MUST write your own. You do not need regex necessarily but it does provide much more concise code.)

Main#2) Identify a Stoplist (either download or compute in a separate code on your own) and store it in a hash structure. (As mentioned later, you will need code to output your hash structure to a output text file.

Main#3) Compute One Inverted Index collectively storing info for All of the above files. See the following links for an explanation of what an Inverted Index is (and is not, ie forward index):

https://www.geeksforgeeks.org/inverted-index/

https://www.geeksforgeeks.org/difference-inverted-index-forward-index/

You are to use either hashmaps or hashtables (separate email will provide tutorial links) for storing the inverted index of your corpus. What information should you store in the inverted index? a) the word; b) the document found in; c) a vector specifying for each occurrence of the word in a document, how many words from beginning of document was it found (for this count include even the stopwords). You need to do this for every word in every document that is not a stopword.

(compile)command line : javac InverterIndex.java
(run)command line : java InverterIndex <word> ***(remove <>)

Type yes or no to stem on command line.
