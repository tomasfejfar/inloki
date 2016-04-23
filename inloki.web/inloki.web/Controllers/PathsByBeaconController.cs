using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace inloki.web.Controllers
{
    public class PathsByBeaconController : ApiController
    {
		// GET api/pathsbybeacon/id
		public IEnumerable<string> Get(string id)
		{
			yield return "path1";
			yield return "path2";
			yield return "path3";
		}
	}
}
