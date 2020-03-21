## What's this?

This is a custom reporter for [ktlint](https://github.com/pinterest/ktlint). It friendly asks ktlint to output an xml file in JUnit format.

## Why on Earth would I need JUnit format? 

Two words: GitLab CI (is that actually two words?). If you are using GitLab and you want to see your ktlint reports on the ~~Pull~~ Merge Request page, then you are welcome. Don't forget to publish the report via `artifacts:reports:junit:`.   

## OK, what do I do?

Simple as 1 2 3
1. Download ktlint-junit-reporter-1.0.jar from this repo's root. Or build it yourself, I don't care.
2. Place it somewhere in your project (I prefer a folder called quality, as you'll see in step 3).
3. Add the reporter to ktlint params: `--reporter=junit,artifact=quality/ktlint-junit-reporter-1.0.jar,output=build/ktlint-report-junit.xml`   

## A jar file. Are you serious?

I'm too lazy to publish this artifact to jCenter, but in a case - let me know, and I'll upload it. Please don't.

