package com.example.dictionary_miniproject;

import java.util.ArrayList;
import java.util.HashMap;

public class DictionaryClass
{
    HashMap<String,String>  dictionary;
    ArrayList<String> result;
    Trie root;
    public DictionaryClass() {
        this.dictionary = new HashMap<>();
        root=new Trie();
        result=new ArrayList<>();
        addWordList();
    }

    public boolean addWord(String word,String meaning)
    {
        TrieDataStructure t=new TrieDataStructure();
        try
        {
            dictionary.put(word,meaning);
            insertNode(word);
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }
    public String getMeaning(String word) {
        if (dictionary.containsKey(word)) {
            return dictionary.get(word);
        } else {
            return "word doesn't exits, sorry :(";
        }
    }
    public void addWordList()
    {
        addWord("mouse", "a small handheld device which is moved across a mat or flat surface to move the cursor on a computer screen:");
        addWord("metaverse","a virtual-reality space in which users can interact with a computer-generated environment and other users.");
        addWord("hard work","a great deal of effort or endurance:");
        addWord("software","the programs and other operating information used by a computer");
        addWord("hardware","tools, machinery, and other durable equipment:");
        addWord("shubh", "Auspicious");
        addWord("Phase", "a stage in the development of something");
        addWord("project", "an individual or collaborative enterprise that is carefully planned to achieve a particular aim");
        addWord("start", "beginning");
        addWord("Depreciating assets ", "Whose value decreases with time");
        addWord("akhitha","beautiful girl");
        addWord("small"," meaning of a amount that is less than normal example sentence rabbit is a small animal");
        addWord("“appear" ,"“meaning: come out\n\n" +"example sentence: please read them if they appear.”\n");
        addWord("care","meaning: maintenance, nursing\n\n" + "example sentence: i don’t care for her.:");
        addWord("drive","meaning: operate the direction\n\n"+"example sentence: i drive for an hour.");
        addWord("everybody","meaning: every person\n\n"+"example sentence: everybody saw it.");
        addWord("final","meaning: last, ultimate\n\n"+"example sentence: my father’s answer is final.");
        addWord("garden","meaning: a piece of ground for growing flowers etc…\n\n"+"example sentence: my sister grows flowers in her garden.");
        addWord("hospital","meaning: an institution for providing medical treatment");
        addWord("although","meaning: it is used to show two opposite statements.\n\n"+"example sentence: although he speaks seldom, he says meaningful words.");
        addWord("because","meaning: used to show reason\n\n"+"example sentence: she usually eats at home, because she likes cooking.");
        addWord("meet","meaning: see\n\n"+"example sentence: the reason why we are holding this meeting is to meet each other.");
        addWord("miss","meaning: put off, yearn\n\n" +"example sentence: i miss your smile.");
        addWord("new","meaning: recent, fresh\n\n" +"example sentence: i’ve got some bad news.");
        addWord("people","meaning: human being\n\n" +"example sentence; i think the people who that live on the island are very friendly.");
        addWord("private","meaning: special, exclusive\n\n" +"example sentence: my father always gets involved in my private life.");
        addWord("region","meaning: an area or division \n\n example sentence: it’s the biggest sports shop in the region.");
        addWord("scientist","meaning: man of science\n\n" +"example sentence: i will be a scientist when i grow up.");
        addWord("student","meaning: a person that is studying at a school or college.\n\n" +"example sentence: she was a good student and a good footballer.");
        addWord("toward","meaning: against\n\n" +"example sentence: the girl ran toward her house.");
        addWord("wear","meaning: draw on\n\n" +"example sentence: she is wearing a blue jacket.");
        addWord("write","meaning: mark on paper\n\n" +"example sentence: i could write mary a note if it would help.");
        addWord("under","meaning: below, beneath\n\n" +"example sentence: is samuel under arrest?");

    }
    public void insertNode(String word)
    {
        Trie temp=root;
        System.out.println(word);
        for(int i=0;i<word.length();i++)
        {
            char curr=word.charAt(i);
            if(temp.children[curr-'a']==null)
            {
                Trie node=new Trie();
                temp.children[curr-'a']=node;
            }
            temp=temp.children[curr-'a'];
        }

        temp.isEndOfWord=true;
    }
    public ArrayList<String> getPrefix(String word)
    {
        Trie temp=root;
        for(int i=0;i<word.length();i++)
        {
            char curr=word.charAt(i);
            if(temp.children[curr-'a']==null)
            {
                return new ArrayList<>();
            }
            temp=temp.children[curr-'a'];
        }
        result=new ArrayList<>();
        traverse(word,temp);
        return result;
    }
    public void traverse(String word,Trie root)
    {
        Trie temp=root;
        ArrayList<String> present=new ArrayList<>();
        for(int i=0;i<26;i++)
        {
            if(temp.children[i]!=null)
            {
                String pre=word+((char)(i+97));
                present.add(pre);
                System.out.println(pre);
                traverse(pre,temp.children[i]);
            }
        }
        for(int i=0;i<present.size();i++)
        {
            result.add(present.get(i));
        }
    }
}
class Trie
{
    Trie[] children;
    char[] chars;
    boolean isEndOfWord;
    Trie()
    {
        children=new Trie[26];
        chars=new char[26];
        isEndOfWord=false;
        for(int i=0;i<26;i++)
        {
            children[i]=null;
        }
    }
}