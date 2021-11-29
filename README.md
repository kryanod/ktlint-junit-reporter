## What's this?

This is a custom reporter for [ktlint](https://github.com/pinterest/ktlint). It makes ktlint output an xml file in JUnit format.

## Why on Earth would I need JUnit format? 

Two words: GitLab CI (is that actually two words?). If you are using GitLab and you want to see your ktlint reports on the ~~Pull~~ Merge Request page, then you are welcome. Don't forget to publish the report via `artifacts:reports:junit:`.   

## OK, what do I do?

Simple as 1 2 3
1. Download ktlint-junit-reporter-1.0.jar from this repo's root. Or build it yourself, the sources are all in here.
2. Place the jar somewhere in your project (I prefer a folder called quality, as you'll see in step 3).
3. Add the reporter to ktlint params: `ktlint --reporter=junit,artifact=quality/ktlint-junit-reporter-1.0.jar,output=build/ktlint-report-junit.xml`   

## A jar file. Are you serious?

I was planning to deploy this plugin to MavenCental, but my laziness seems to have paid off: having custom reporters as maven plugins has been [deprecated](https://github.com/pinterest/ktlint#creating-a-reporter).
