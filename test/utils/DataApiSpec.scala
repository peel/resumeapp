package utils

import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._
import models.Author

@RunWith(classOf[JUnitRunner])
class DataApiSpec extends Specification{
  val dataApi= new DataApi with MemDataSource

  "DataApi" should {
    "create" in {
        dataApi.getCvData[Author]("") must haveClass[Author]
      }
    }
}

trait MemDataSource extends DataSource{
    def getSource(filename: String) = {
      """
        |{
        |    "name": "John Doe",
        |    "role": "Developer",
        |    "about": "Cool",
        |    "contact": {
        |    },
        |    "social": {
        |    },
        |    "jobs":[
        |        {
        |            "period": "Jan 2009 - Jan 2010",
        |            "company": "Test",
        |            "role": "Developer",
        |            "location": "Test, PL",
        |            "description": "Cool"
        |        }
        |    ],
        |    "schools":[
        |        {
        |            "period": "2002 - 2005",
        |            "name": "School",
        |            "location": "Place, Pl",
        |            "description": "Stuff"
        |        }
        |    ],
        |    "skills":[
        |        {
        |            "name": "Skill",
        |            "level": 90,
        |            "description": ""
        |        }
        |    ],
        |    "competencies":[
        |        {
        |            "name": "Competency",
        |            "level": 50,
        |            "description": "Competency"
        |        }
        |    ],
        |    "interests":[
        |        {
        |            "name": "Hobby",
        |            "level": 75,
        |            "description": "Desc"
        |        }
        |    ]
        |}
        |
      """.stripMargin
  }
}

