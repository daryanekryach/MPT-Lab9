# MPT-Lab9
[![Build Status](https://travis-ci.org/daryanekryach/MPT-Lab9.svg?branch=master)](https://travis-ci.org/daryanekryach/MPT-Lab9)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/c7fc8e3129a0403388c7427ff35de579)](https://www.codacy.com/app/daryanekryach/MPT-Lab9?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=daryanekryach/MPT-Lab9&amp;utm_campaign=Badge_Grade)
[![codecov](https://codecov.io/gh/daryanekryach/MPT-Lab9/branch/master/graph/badge.svg)](https://codecov.io/gh/daryanekryach/MPT-Lab9)

*Repository for 9th  MPT lab using GitHubAPI, PostgreSQL and PreparedStatments*

***

## TASK
<p>Create tables: Users, Contributors, Repository owners, Repositories, Languages. Fill in all the tables you created with data from 8 labs. Collect the data for the last 8 weeks. Eliminate duplication. Write 5 queries to the database. For example, write requests that will output all users who committed in 3 or more repositories. Convert your query results into a data model and output them to the console. Use PostgreSQL and PreparedStatement.</p>

## RESULTS
### Processing data from GitHub
> Execution time is 849102 ms or 14 min 9 sec<br/>Used memory is 10.601783752441406 mb
### Inserting data to PostgreSQL
> Execution time is 86563 ms or 1 min 26 sec<br/>Used memory is 9.328826904296875 mb

### First 10 most contibution-wise active users 
1. invalid-email-address - 29
2. gitter-badger - 19
3. msftgits - 16
4. microsoftopensource - 15
5. cclauss - 14
6. CoderSavior - 13
7. mikeal - 10
8. llSourcell - 10
9. angular-cli - 8
10. stackhou - 8
### First 10 users who own the most repositories
1. Microsoft - 16
2. CoderSavior - 13
3. google - 12
4. llSourcell - 10
5. mikeal - 9
6. awslabs - 9
7. stackhou - 8
8. yutao331763646 - 7
9. keijiro - 7
10. fossasia - 6
### 10 most popular languages
1. JavaScript - 843
2. Python - 536
3. none - 367
4. Java - 323
5. Go - 224
6. C++ - 163
7. Swift - 155
8. C# - 145
9. PHP - 144
10. HTML - 126
### THE MOST COMMITTED REPOSITORY IS hacktoberfest WITH 100 COMMITS
