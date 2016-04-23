using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace inloki.web.Models
{
	public class Path
	{
		public string name { get; set; }
		public string description { get; set; }

		public IEnumerable<string> waypoints { get; set; }
	}
}