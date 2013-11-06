package utils

import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._
import models._

@RunWith(classOf[JUnitRunner])
class DataApiSpec extends Specification{
  val dataApi= new DataApi with MemDataSource
  val cvData: Author = dataApi.getCvData[Author]("")

  "CvDataParser" should {
    "create" in {
      "an Author object" in {
        cvData must haveClass[Author]
      }
      "a Jobs collection" in {
        cvData.jobs(0) must haveClass[Job]
      }
      "a Schools collection" in {
        cvData.schools(0) must haveClass[School]
      }
      "a Skills collection" in {
        cvData.skills(0) must haveClass[Skill]
      }
      "an Interests collection" in {
        cvData.interests(0) must haveClass[Interest]
      }
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

