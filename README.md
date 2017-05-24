### A user in youtube asked me

I have got a question. Is that possible to get known, which grammar rule was recognized? Some variable which returns "feelings" , "syntax" etc. ??

For example we have the grammar:

```
  #JSGF V1.0;

  /**
  * JSGF Grammar 
  */

  grammar grammar;

  public <feelings>  = ( how are you | say hello);
  public <voices>  = ( change to voice one  | change to voice two | change to voice three );
  public <amazing>  = ( say amazing | what day is today );
  public <nervous>  = (who is your daddy | obey monster | hey boss);
  public <number> = ( zero | one | two | three | four | five | six | seven | nine | ten
                   | eleven | twelve | thirteen | fourteen | fifteen | sixteen | seventeen | eighteen | nineteen | twenty 
                   | thirty | forty | fifty | sixty | seventy | eighty | ninety 
		           | hundred | thousand | million | billion)+;                   
  public <syntax> = <number>{1} (plus | minus | multiply | division){1} <number>{1}; 
```
---

### My answer

Yes you can do it using the simple library i have written here , just one class :)

### Warning

I have to fix some things like for example if you search for the word `zero` it will return the rule `number` but not the rule `syntax` which obviously contains
the word `zero` but it has it like `<number>{1}` , so i must add more code to detect this also.
