using inloki.web.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace inloki.web.Controllers
{
    public class PathsController : ApiController
    {
		// GET api/paths/55
		public Path Get(string id)
		{
			return Database.Load().Paths.SingleOrDefault();
		}
	}
}
