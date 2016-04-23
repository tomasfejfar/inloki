using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace inloki.web.Models
{
	public class Step
	{
		public Beacon currentbeacon { get; set; }
		public Beacon nextbeacon { get; set; }
		public string navigationinstructions { get; set; }
		public Path path { get; set; }
	}
}