# Inverter-Index
Class Project

Main#1) Obtain corpus of documents for the semester. To do so, come up with 10 neutral queries for your search engine. You are to then download the first 20 webpage responses that the search engine returns with, for each of the 10 queries. There will be a total of 200 html files.

Main#2) Get a Stoplist and store it in a hash structure.

Main#3) Compute One Inverted Index collectively storing info for All of the above files. Use either hashmaps or hashtables for storing the inverted index of your corpus. What information should you store in the inverted index? a) the word; b) the document found in; c) a vector specifying for each occurrence of the word in a document, how many words from beginning of document was it found. You need to do this for every word in every document that is not a stopword.

(compile)command line : javac InverterIndex.java
(run)command line : java InverterIndex <word> ***(remove <>)

Type yes or no to stem on command line.
