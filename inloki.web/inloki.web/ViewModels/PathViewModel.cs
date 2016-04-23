using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace inloki.web.ViewModels
{
	public class PathViewModel
	{
		public string name { get; set; }
		public string description { get; set; }

		public IEnumerable<BeaconViewModel> Beacons { get; set; }
	}
}